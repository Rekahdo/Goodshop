package com.rekahdo.goodshop.file_service.utilities;//package com.rekahdo.goodshop.file_service.utilities;
//
//import java.io.*;
//import java.util.zip.Deflater;
//import java.util.zip.Inflater;
//import java.util.zip.DataFormatException;
//
///**
// * Production-grade image compression utility with configurable compression levels,
// * streaming support, and comprehensive error handling.
// */
//public class ImageCompressionService {
//
//    // Compression levels with meaningful names
//    public enum CompressionLevel {
//        FAST(Deflater.BEST_SPEED),
//        BALANCED(Deflater.DEFAULT_COMPRESSION),
//        MAX(Deflater.BEST_COMPRESSION);
//
//        private final int level;
//
//        CompressionLevel(int level) {
//            this.level = level;
//        }
//
//        public int getValue() {
//            return level;
//        }
//    }
//
//    // Configuration for compression
//    public static class CompressionConfig {
//        private CompressionLevel level = CompressionLevel.BALANCED;
//        private int bufferSize = 8192; // 8KB buffer by default
//        private boolean useGzipHeader = false; // true for gzip compatibility
//
//        public CompressionConfig() {}
//
//        public CompressionConfig level(CompressionLevel level) {
//            this.level = level;
//            return this;
//        }
//
//        public CompressionConfig bufferSize(int bufferSize) {
//            if (bufferSize <= 0) {
//                throw new IllegalArgumentException("Buffer size must be positive");
//            }
//            this.bufferSize = bufferSize;
//            return this;
//        }
//
//        public CompressionConfig useGzipHeader(boolean useGzipHeader) {
//            this.useGzipHeader = useGzipHeader;
//            return this;
//        }
//    }
//
//    // Result wrapper with metadata
//    public static class CompressionResult {
//        private final byte[] data;
//        private final long originalSize;
//        private final long compressedSize;
//        private final double compressionRatio;
//
//        public CompressionResult(byte[] data, long originalSize) {
//            this.data = data;
//            this.originalSize = originalSize;
//            this.compressedSize = data.length;
//            this.compressionRatio = originalSize > 0 ?
//                (1.0 - ((double) compressedSize / originalSize)) * 100 : 0.0;
//        }
//
//        public byte[] getData() { return data; }
//        public long getOriginalSize() { return originalSize; }
//        public long getCompressedSize() { return compressedSize; }
//        public double getCompressionRatio() { return compressionRatio; }
//
//        @Override
//        public String toString() {
//            return String.format(
//                "CompressionResult{original=%dB, compressed=%dB, ratio=%.2f%%}",
//                originalSize, compressedSize, compressionRatio
//            );
//        }
//    }
//
//    /**
//     * Compress image data with default configuration.
//     *
//     * @param data Raw image bytes to compress
//     * @return CompressionResult containing compressed data and metadata
//     * @throws CompressionException if compression fails
//     */
//    public static CompressionResult compress(byte[] data) throws CompressionException {
//        return compress(data, new CompressionConfig());
//    }
//
//    /**
//     * Compress image data with custom configuration.
//     *
//     * @param data Raw image bytes to compress
//     * @param config Compression configuration
//     * @return CompressionResult containing compressed data and metadata
//     * @throws CompressionException if compression fails
//     */
//    public static CompressionResult compress(byte[] data, CompressionConfig config)
//            throws CompressionException {
//        if (data == null) {
//            throw new IllegalArgumentException("Input data cannot be null");
//        }
//        if (config == null) {
//            throw new IllegalArgumentException("Config cannot be null");
//        }
//
//        Deflater deflater = null;
//        ByteArrayOutputStream outputStream = null;
//
//        try {
//            // Initialize deflater with config
//            deflater = new Deflater(config.level.getValue(), config.useGzipHeader);
//            deflater.setInput(data);
//            deflater.finish();
//
//            // Use try-with-resources for auto-closing
//            outputStream = new ByteArrayOutputStream(data.length);
//            byte[] buffer = new byte[config.bufferSize];
//            long totalCompressed = 0;
//
//            while (!deflater.finished()) {
//                int compressedSize = deflater.deflate(buffer);
//                if (compressedSize > 0) {
//                    outputStream.write(buffer, 0, compressedSize);
//                    totalCompressed += compressedSize;
//                }
//            }
//
//            // Log compression metrics (production: use proper logging framework)
//            logCompressionMetrics(data.length, totalCompressed);
//
//            return new CompressionResult(outputStream.toByteArray(), data.length);
//
//        } catch (Exception e) {
//            throw new CompressionException("Failed to compress image data", e);
//        } finally {
//            // Ensure resources are cleaned up
//            if (deflater != null) {
//                deflater.end();
//            }
//            closeQuietly(outputStream);
//        }
//    }
//
//    /**
//     * Decompress previously compressed image data.
//     *
//     * @param compressedData Compressed byte data
//     * @return Decompressed original image data
//     * @throws CompressionException if decompression fails
//     */
//    public static byte[] decompress(byte[] compressedData) throws CompressionException {
//        return decompress(compressedData, new CompressionConfig());
//    }
//
//    /**
//     * Decompress previously compressed image data.
//     *
//     * @param compressedData Compressed byte data
//     * @param config Original compression configuration (for buffer size)
//     * @return Decompressed original image data
//     * @throws CompressionException if decompression fails
//     */
//    public static byte[] decompress(byte[] compressedData, CompressionConfig config)
//            throws CompressionException {
//        if (compressedData == null) {
//            throw new IllegalArgumentException("Compressed data cannot be null");
//        }
//        if (config == null) {
//            throw new IllegalArgumentException("Config cannot be null");
//        }
//
//        Inflater inflater = null;
//        ByteArrayOutputStream outputStream = null;
//
//        try {
//            inflater = new Inflater(config.useGzipHeader);
//            inflater.setInput(compressedData);
//
//            outputStream = new ByteArrayOutputStream(compressedData.length * 2);
//            byte[] buffer = new byte[config.bufferSize];
//
//            while (!inflater.finished()) {
//                int count = inflater.inflate(buffer);
//                outputStream.write(buffer, 0, count);
//            }
//
//            return outputStream.toByteArray();
//
//        } catch (DataFormatException e) {
//            throw new CompressionException(
//                "Invalid compressed data format. Data may be corrupted or not DEFLATE compressed",
//                e
//            );
//        } catch (Exception e) {
//            throw new CompressionException("Failed to decompress image data", e);
//        } finally {
//            if (inflater != null) {
//                inflater.end();
//            }
//            closeQuietly(outputStream);
//        }
//    }
//
//    /**
//     * Stream-based compression for large images (memory efficient).
//     *
//     * @param inputStream Stream of image data to compress
//     * @param outputStream Stream to write compressed data to
//     * @param config Compression configuration
//     * @return CompressionResult with metadata
//     * @throws CompressionException if compression fails
//     * @throws IOException if stream operations fail
//     */
//    public static CompressionResult compressStream(
//            InputStream inputStream,
//            OutputStream outputStream,
//            CompressionConfig config) throws CompressionException, IOException {
//
//        if (inputStream == null || outputStream == null) {
//            throw new IllegalArgumentException("Streams cannot be null");
//        }
//
//        Deflater deflater = null;
//        CountingInputStream countingInput = null;
//        CountingOutputStream countingOutput = null;
//
//        try {
//            // Wrap streams to track sizes
//            countingInput = new CountingInputStream(inputStream);
//            countingOutput = new CountingOutputStream(outputStream);
//
//            deflater = new Deflater(config.level.getValue(), config.useGzipHeader);
//
//            byte[] inputBuffer = new byte[config.bufferSize];
//            byte[] outputBuffer = new byte[config.bufferSize];
//
//            int bytesRead;
//            while ((bytesRead = countingInput.read(inputBuffer)) != -1) {
//                deflater.setInput(inputBuffer, 0, bytesRead);
//
//                while (!deflater.needsInput()) {
//                    int compressedSize = deflater.deflate(outputBuffer);
//                    countingOutput.write(outputBuffer, 0, compressedSize);
//                }
//            }
//
//            deflater.finish();
//
//            while (!deflater.finished()) {
//                int compressedSize = deflater.deflate(outputBuffer);
//                countingOutput.write(outputBuffer, 0, compressedSize);
//            }
//
//            logCompressionMetrics(countingInput.getByteCount(), countingOutput.getByteCount());
//
//            return new CompressionResult(null, countingInput.getByteCount());
//
//        } catch (IOException e) {
//            throw new CompressionException("Failed to compress stream", e);
//        } finally {
//            if (deflater != null) {
//                deflater.end();
//            }
//            // Don't close the streams here - let the caller handle them
//        }
//    }
//
//    /**
//     * Stream-based decompression for large images.
//     *
//     * @param inputStream Stream of compressed data
//     * @param outputStream Stream to write decompressed data to
//     * @param config Compression configuration
//     * @throws CompressionException if decompression fails
//     * @throws IOException if stream operations fail
//     */
//    public static void decompressStream(
//            InputStream inputStream,
//            OutputStream outputStream,
//            CompressionConfig config) throws CompressionException, IOException {
//
//        Inflater inflater = null;
//
//        try {
//            inflater = new Inflater(config.useGzipHeader);
//
//            byte[] inputBuffer = new byte[config.bufferSize];
//            byte[] outputBuffer = new byte[config.bufferSize];
//
//            int bytesRead;
//            while ((bytesRead = inputStream.read(inputBuffer)) != -1) {
//                inflater.setInput(inputBuffer, 0, bytesRead);
//
//                while (!inflater.needsInput()) {
//                    int decompressedSize = inflater.inflate(outputBuffer);
//                    outputStream.write(outputBuffer, 0, decompressedSize);
//                }
//            }
//
//            while (!inflater.finished()) {
//                int decompressedSize = inflater.inflate(outputBuffer);
//                outputStream.write(outputBuffer, 0, decompressedSize);
//            }
//
//        } catch (DataFormatException e) {
//            throw new CompressionException("Invalid compressed data format", e);
//        } catch (IOException e) {
//            throw new CompressionException("Failed to decompress stream", e);
//        } finally {
//            if (inflater != null) {
//                inflater.end();
//            }
//        }
//    }
//
//    // Helper methods
//    private static void closeQuietly(Closeable closeable) {
//        if (closeable != null) {
//            try {
//                closeable.close();
//            } catch (IOException e) {
//                // Log warning in production
//                System.err.println("Warning: Failed to close resource: " + e.getMessage());
//            }
//        }
//    }
//
//    private static void logCompressionMetrics(long originalSize, long compressedSize) {
//        // In production, use SLF4J or similar logging framework
//        if (originalSize > 0) {
//            double ratio = (1.0 - ((double) compressedSize / originalSize)) * 100;
//            System.out.printf("Compression: %dB -> %dB (%.2f%% compression)%n",
//                originalSize, compressedSize, ratio);
//        }
//    }
//
//    // Custom exception for better error handling
//    public static class CompressionException extends Exception {
//        public CompressionException(String message) {
//            super(message);
//        }
//
//        public CompressionException(String message, Throwable cause) {
//            super(message, cause);
//        }
//    }
//
//    // Helper classes for counting bytes in streams
//    private static class CountingInputStream extends FilterInputStream {
//        private long byteCount = 0;
//
//        protected CountingInputStream(InputStream in) {
//            super(in);
//        }
//
//        @Override
//        public int read() throws IOException {
//            int result = super.read();
//            if (result != -1) {
//                byteCount++;
//            }
//            return result;
//        }
//
//        @Override
//        public int read(byte[] b, int off, int len) throws IOException {
//            int bytesRead = super.read(b, off, len);
//            if (bytesRead != -1) {
//                byteCount += bytesRead;
//            }
//            return bytesRead;
//        }
//
//        public long getByteCount() {
//            return byteCount;
//        }
//    }
//
//    private static class CountingOutputStream extends FilterOutputStream {
//        private long byteCount = 0;
//
//        protected CountingOutputStream(OutputStream out) {
//            super(out);
//        }
//
//        @Override
//        public void write(int b) throws IOException {
//            super.write(b);
//            byteCount++;
//        }
//
//        @Override
//        public void write(byte[] b, int off, int len) throws IOException {
//            super.write(b, off, len);
//            byteCount += len;
//        }
//
//        public long getByteCount() {
//            return byteCount;
//        }
//    }
//}