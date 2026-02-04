package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.BankDto;
import com.rekahdo.goodshop.vendor_service.dtos.entities.BusinessDto;
import com.rekahdo.goodshop.vendor_service.dtos.entities.VendorDto;
import com.rekahdo.goodshop.vendor_service.entities.Bank;
import com.rekahdo.goodshop.vendor_service.entities.Business;
import com.rekahdo.goodshop.vendor_service.entities.Unresolved;
import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T13:40:26+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class VendorMapperImpl implements VendorMapper {

    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private BankMapper bankMapper;
    @Autowired
    private UnresolvedMapper unresolvedMapper;

    @Override
    public Vendor toEntity(VendorDto second) {
        if ( second == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        vendor.setId( second.getId() );
        vendor.setUid( second.getUid() );
        vendor.setUserId( second.getUserId() );
        if ( second.getApprovalStatus() != null ) {
            vendor.setApprovalStatus( second.getApprovalStatus().ordinal() );
        }
        vendor.setBusiness( businessDtoToBusiness( second.getBusiness() ) );
        vendor.setBank( bankDtoToBank( second.getBank() ) );
        vendor.setUnresolved( unresolvedMapper.toEntityList( second.getUnresolved() ) );
        vendor.setRegisteredAt( second.getRegisteredAt() );

        afterMappingToEntity( vendor, second );

        return vendor;
    }

    @Override
    public List<Vendor> toEntityList(List<VendorDto> seconds) {
        if ( seconds == null ) {
            return null;
        }

        List<Vendor> list = new ArrayList<Vendor>( seconds.size() );
        for ( VendorDto vendorDto : seconds ) {
            list.add( toEntity( vendorDto ) );
        }

        return list;
    }

    @Override
    public List<VendorDto> toSecondList(List<Vendor> entities) {
        if ( entities == null ) {
            return null;
        }

        List<VendorDto> list = new ArrayList<VendorDto>( entities.size() );
        for ( Vendor vendor : entities ) {
            list.add( toSecond( vendor ) );
        }

        return list;
    }

    @Override
    public void updateEntity(VendorDto source, Vendor target) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            target.setId( source.getId() );
        }
        if ( source.getUid() != null ) {
            target.setUid( source.getUid() );
        }
        if ( source.getUserId() != null ) {
            target.setUserId( source.getUserId() );
        }
        if ( source.getApprovalStatus() != null ) {
            target.setApprovalStatus( source.getApprovalStatus().ordinal() );
        }
        if ( source.getBusiness() != null ) {
            if ( target.getBusiness() == null ) {
                target.setBusiness( new Business() );
            }
            businessDtoToBusiness1( source.getBusiness(), target.getBusiness() );
        }
        if ( source.getBank() != null ) {
            if ( target.getBank() == null ) {
                target.setBank( new Bank() );
            }
            bankDtoToBank1( source.getBank(), target.getBank() );
        }
        if ( target.getUnresolved() != null ) {
            List<Unresolved> list = unresolvedMapper.toEntityList( source.getUnresolved() );
            if ( list != null ) {
                target.getUnresolved().clear();
                target.getUnresolved().addAll( list );
            }
        }
        else {
            List<Unresolved> list = unresolvedMapper.toEntityList( source.getUnresolved() );
            if ( list != null ) {
                target.setUnresolved( list );
            }
        }
        if ( source.getRegisteredAt() != null ) {
            target.setRegisteredAt( source.getRegisteredAt() );
        }

        afterMappingToEntity( target, source );
    }

    @Override
    public VendorDto toSecond(Vendor entity) {
        if ( entity == null ) {
            return null;
        }

        VendorDto vendorDto = new VendorDto();

        vendorDto.setContactPerson( contactMapper.toDto( entity.getContact() ) );
        vendorDto.setId( entity.getId() );
        vendorDto.setUid( entity.getUid() );
        vendorDto.setUserId( entity.getUserId() );
        vendorDto.setRegisteredAt( entity.getRegisteredAt() );
        vendorDto.setBusiness( businessMapper.toDto( entity.getBusiness() ) );
        vendorDto.setBank( bankMapper.toDto( entity.getBank() ) );
        vendorDto.setUnresolved( unresolvedMapper.toSecondList( entity.getUnresolved() ) );

        afterMappingToSecond( vendorDto, entity );

        return vendorDto;
    }

    protected Business businessDtoToBusiness(BusinessDto businessDto) {
        if ( businessDto == null ) {
            return null;
        }

        Business business = new Business();

        business.setId( businessDto.getId() );
        business.setRegistrationName( businessDto.getRegistrationName() );
        business.setTradingName( businessDto.getTradingName() );
        business.setEmail( businessDto.getEmail() );
        business.setEmailVerified( businessDto.isEmailVerified() );

        return business;
    }

    protected Bank bankDtoToBank(BankDto bankDto) {
        if ( bankDto == null ) {
            return null;
        }

        Bank bank = new Bank();

        bank.setId( bankDto.getId() );
        bank.setAccountName( bankDto.getAccountName() );
        bank.setBankName( bankDto.getBankName() );
        bank.setAccountNumber( bankDto.getAccountNumber() );
        if ( bankDto.getAccountType() != null ) {
            bank.setAccountType( bankDto.getAccountType().ordinal() );
        }

        return bank;
    }

    protected void businessDtoToBusiness1(BusinessDto businessDto, Business mappingTarget) {
        if ( businessDto == null ) {
            return;
        }

        if ( businessDto.getId() != null ) {
            mappingTarget.setId( businessDto.getId() );
        }
        if ( businessDto.getRegistrationName() != null ) {
            mappingTarget.setRegistrationName( businessDto.getRegistrationName() );
        }
        if ( businessDto.getTradingName() != null ) {
            mappingTarget.setTradingName( businessDto.getTradingName() );
        }
        if ( businessDto.getEmail() != null ) {
            mappingTarget.setEmail( businessDto.getEmail() );
        }
        mappingTarget.setEmailVerified( businessDto.isEmailVerified() );
    }

    protected void bankDtoToBank1(BankDto bankDto, Bank mappingTarget) {
        if ( bankDto == null ) {
            return;
        }

        if ( bankDto.getId() != null ) {
            mappingTarget.setId( bankDto.getId() );
        }
        if ( bankDto.getAccountName() != null ) {
            mappingTarget.setAccountName( bankDto.getAccountName() );
        }
        if ( bankDto.getBankName() != null ) {
            mappingTarget.setBankName( bankDto.getBankName() );
        }
        if ( bankDto.getAccountNumber() != null ) {
            mappingTarget.setAccountNumber( bankDto.getAccountNumber() );
        }
        if ( bankDto.getAccountType() != null ) {
            mappingTarget.setAccountType( bankDto.getAccountType().ordinal() );
        }
    }
}
