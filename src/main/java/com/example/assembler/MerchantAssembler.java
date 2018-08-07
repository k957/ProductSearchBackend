package com.example.assembler;

import org.springframework.stereotype.Component;

import com.example.dto.MerchantDto;
import com.example.model.Merchant;

@Component
public class MerchantAssembler {

	public Merchant createMerchantEntity(MerchantDto merchantDto) {
		Merchant merchant = new Merchant();
		System.out.println(merchant);
		merchant.setName(merchantDto.getName());
		merchant.setDisplayName(merchantDto.getDisplayName());
		merchant.setMailId(merchantDto.getMailId());
		merchant.setMobileNo(merchantDto.getMobileNo());
		return merchant;
	}
}
