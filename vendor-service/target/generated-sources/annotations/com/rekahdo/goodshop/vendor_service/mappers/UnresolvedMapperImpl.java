package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.UnresolvedDto;
import com.rekahdo.goodshop.vendor_service.entities.Unresolved;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T13:40:26+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class UnresolvedMapperImpl implements UnresolvedMapper {

    @Override
    public Unresolved toEntity(UnresolvedDto second) {
        if ( second == null ) {
            return null;
        }

        Unresolved unresolved = new Unresolved();

        unresolved.setId( second.getId() );
        if ( second.getReason() != null ) {
            unresolved.setReason( second.getReason().name() );
        }
        unresolved.setQueried( second.isQueried() );
        unresolved.setGeneratedAt( second.getGeneratedAt() );

        afterMappingToEntity( unresolved, second );

        return unresolved;
    }

    @Override
    public List<Unresolved> toEntityList(List<UnresolvedDto> seconds) {
        if ( seconds == null ) {
            return null;
        }

        List<Unresolved> list = new ArrayList<Unresolved>( seconds.size() );
        for ( UnresolvedDto unresolvedDto : seconds ) {
            list.add( toEntity( unresolvedDto ) );
        }

        return list;
    }

    @Override
    public List<UnresolvedDto> toSecondList(List<Unresolved> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UnresolvedDto> list = new ArrayList<UnresolvedDto>( entities.size() );
        for ( Unresolved unresolved : entities ) {
            list.add( toSecond( unresolved ) );
        }

        return list;
    }

    @Override
    public void updateEntity(UnresolvedDto source, Unresolved target) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            target.setId( source.getId() );
        }
        if ( source.getReason() != null ) {
            target.setReason( source.getReason().name() );
        }
        target.setQueried( source.isQueried() );
        if ( source.getGeneratedAt() != null ) {
            target.setGeneratedAt( source.getGeneratedAt() );
        }

        afterMappingToEntity( target, source );
    }

    @Override
    public UnresolvedDto toSecond(Unresolved unresolved) {
        if ( unresolved == null ) {
            return null;
        }

        UnresolvedDto unresolvedDto = new UnresolvedDto();

        unresolvedDto.setId( unresolved.getId() );
        unresolvedDto.setQueried( unresolved.isQueried() );
        unresolvedDto.setGeneratedAt( unresolved.getGeneratedAt() );

        afterMappingToSecond( unresolvedDto, unresolved );

        return unresolvedDto;
    }
}
