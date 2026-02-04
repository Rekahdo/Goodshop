package com.rekahdo.goodshop.file_service.mappers;

import com.rekahdo.goodshop.file_service.dtos.clients.FileClient;
import com.rekahdo.goodshop.file_service.dtos.entities.FileDto;
import com.rekahdo.goodshop.file_service.entities.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T13:52:22+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class FileMapperImpl implements FileMapper {

    @Override
    public List<FileDto> toDtoList(List<File> entities) {
        if ( entities == null ) {
            return null;
        }

        List<FileDto> list = new ArrayList<FileDto>( entities.size() );
        for ( File file : entities ) {
            list.add( toDto( file ) );
        }

        return list;
    }

    @Override
    public List<FileClient> toClientList(List<File> entities) {
        if ( entities == null ) {
            return null;
        }

        List<FileClient> list = new ArrayList<FileClient>( entities.size() );
        for ( File file : entities ) {
            list.add( toClient( file ) );
        }

        return list;
    }

    @Override
    public FileDto toDto(File entity) {
        if ( entity == null ) {
            return null;
        }

        FileDto fileDto = new FileDto();

        beforeMappingToDto( fileDto, entity );

        fileDto.setId( entity.getId() );
        fileDto.setName( entity.getName() );
        fileDto.setType( entity.getType() );
        fileDto.setSize( entity.getSize() );
        fileDto.setUserId( entity.getUserId() );
        fileDto.setUpdatedAt( entity.getUpdatedAt() );

        afterMappingToDto( fileDto, entity );

        return fileDto;
    }

    @Override
    public FileClient toClient(File entity) {
        if ( entity == null ) {
            return null;
        }

        FileClient fileClient = new FileClient();

        beforeMappingToClient( fileClient, entity );

        fileClient.setId( entity.getId() );
        fileClient.setName( entity.getName() );
        fileClient.setType( entity.getType() );
        fileClient.setSize( entity.getSize() );
        fileClient.setUserId( entity.getUserId() );
        fileClient.setUpdatedAt( entity.getUpdatedAt() );

        afterMappingToClient( fileClient, entity );

        return fileClient;
    }
}
