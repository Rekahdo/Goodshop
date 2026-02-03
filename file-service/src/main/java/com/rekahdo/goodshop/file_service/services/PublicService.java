package com.rekahdo.goodshop.file_service.services;

import com.rekahdo.goodshop.file_service.dtos.entities.ImageDto;
import com.rekahdo.goodshop.file_service.entities.File;
import com.rekahdo.goodshop.file_service.utilities.FileUtils;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicService {

    public byte[] resizeImage(MultipartFile image, int width, int height,
                              boolean keepAspectRatio, String downloadTo) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Thumbnails.of(image.getInputStream())
                .size(width, height)
                .keepAspectRatio(keepAspectRatio)
                .toOutputStream(outputStream);

        downloadTo(outputStream.toByteArray(), image, downloadTo);
        return outputStream.toByteArray();
    }

    public byte[] compressImageToKb(MultipartFile image, Integer kb, String downloadTo) {
        return null;
    }

    public byte[] scaleImage(MultipartFile image, double factor, String downloadTo) throws IOException {
        // 1. Read the input stream into a BufferedImage
        BufferedImage originalImage = ImageIO.read(image.getInputStream());

        // 2. Use Thumbnailator to scale the image
        BufferedImage thumbnail = Thumbnails.of(originalImage)
                .scale(factor)
                .asBufferedImage();

        // 3. Write the buffered image to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // You can change "png" to "jpg" or use image.getContentType() logic
        ImageIO.write(thumbnail, "png", outputStream);

        downloadTo(outputStream.toByteArray(), image, downloadTo);
        return outputStream.toByteArray();
    }

    public byte[] cropImage(MultipartFile image, Positions position, int x, int y, int w, int h, boolean makeSquare, String downloadTo) throws IOException {
        // If true, w and h should be of the same value
        if(makeSquare) h = w;

        // If true, crop the center of the image
        if(position != null) return cropImageToPosition(image, position, w, h, downloadTo);

        // Otherwise, crop based on the axis
        return cropImageToAxis(image, x, y, w, h, downloadTo);
    }

    private byte[] cropImageToPosition(MultipartFile image, Positions position, int width, int height, String downloadTo) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Thumbnails.of(image.getInputStream())
                .sourceRegion(position, width, height) // Define region to keep
                .size(width, height)
                .toOutputStream(outputStream);

        downloadTo(outputStream.toByteArray(), image, downloadTo);
        return outputStream.toByteArray();
    }

    private byte[] cropImageToAxis(MultipartFile image, int x, int y, int w, int h, String downloadTo) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        BufferedImage cropped = bufferedImage.getSubimage(x, y, w, h);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(cropped, "png", outputStream);

        downloadTo(outputStream.toByteArray(), image, downloadTo);
        return outputStream.toByteArray();
    }

    public ImageDto imageInfo(MultipartFile image) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        ImageDto imageDto = new ImageDto();
        imageDto.setName(image.getOriginalFilename());
        imageDto.setType(image.getContentType());
        imageDto.setWidth(bufferedImage.getWidth());
        imageDto.setHeight(bufferedImage.getHeight());
        imageDto.setBytes(image.getSize());
        imageDto.setSize(FileUtils.getReadableFileSize(image.getSize()));
        return imageDto;
    }

    private void downloadTo(byte[] imageBytes, MultipartFile image, String downloadTo) throws IOException {
        downloadTo(imageBytes, image.getOriginalFilename(), downloadTo);
    }

    private void downloadTo(List<File> fileList, String downloadTo) throws IOException {
        fileList.forEach(file -> {
            try {
                downloadTo(file.getFileData(), file.getName(), downloadTo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void downloadTo(byte[] imageBytes, String fileName, String downloadTo) throws IOException {
        if(downloadTo == null || downloadTo.isEmpty()) return;

        downloadTo = downloadTo.endsWith("/") ? downloadTo : downloadTo.concat("/");
        Path path = Paths.get(downloadTo + fileName)
                .toAbsolutePath().normalize();

        Files.createDirectories(path.getParent());
        Files.write(path, imageBytes);
    }

}
