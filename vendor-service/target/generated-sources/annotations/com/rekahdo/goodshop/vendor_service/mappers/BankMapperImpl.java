package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.BankDto;
import com.rekahdo.goodshop.vendor_service.dtos.request.BankRequest;
import com.rekahdo.goodshop.vendor_service.entities.Bank;
import com.rekahdo.goodshop.vendor_service.enums.AccountType;
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
public class BankMapperImpl implements BankMapper {

    @Override
    public BankDto toDto(Bank entity) {
        if ( entity == null ) {
            return null;
        }

        BankDto bankDto = new BankDto();

        beforeMappingToDto( bankDto, entity );

        bankDto.setId( entity.getId() );
        bankDto.setAccountName( entity.getAccountName() );
        bankDto.setBankName( entity.getBankName() );
        bankDto.setAccountNumber( entity.getAccountNumber() );
        if ( entity.getAccountType() != null ) {
            bankDto.setAccountType( AccountType.values()[ entity.getAccountType() ] );
        }

        afterMappingToDto( bankDto, entity );

        return bankDto;
    }

    @Override
    public List<BankDto> toDtoList(List<Bank> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BankDto> list = new ArrayList<BankDto>( entities.size() );
        for ( Bank bank : entities ) {
            list.add( toDto( bank ) );
        }

        return list;
    }

    @Override
    public List<Bank> toEntityList(List<BankRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Bank> list = new ArrayList<Bank>( requests.size() );
        for ( BankRequest bankRequest : requests ) {
            list.add( toEntity( bankRequest ) );
        }

        return list;
    }

    @Override
    public Bank toEntity(BankRequest bankRequest) {
        if ( bankRequest == null ) {
            return null;
        }

        Bank bank = new Bank();

        beforeMappingToEntity( bank, bankRequest );

        bank.setAccountName( bankRequest.accountName() );
        bank.setBankName( bankRequest.bankName() );
        bank.setAccountNumber( bankRequest.accountNumber() );

        afterMappingToEntity( bank, bankRequest );

        return bank;
    }

    @Override
    public void updateEntity(BankRequest source, Bank target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        if ( source.accountName() != null ) {
            target.setAccountName( source.accountName() );
        }
        if ( source.bankName() != null ) {
            target.setBankName( source.bankName() );
        }
        if ( source.accountNumber() != null ) {
            target.setAccountNumber( source.accountNumber() );
        }
        if ( source.accountType() != null ) {
            target.setAccountType( source.accountType().ordinal() );
        }

        afterMappingToEntity( target, source );
    }
}
