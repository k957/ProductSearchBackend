
package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assembler.FeedAssembler;
import com.example.dto.FeedDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Feed;
import com.example.model.Merchant;
import com.example.model.Product;
import com.example.model.Store;
import com.example.repository.IFeedRepository;
import com.example.repository.IMerchantRepository;
import com.example.repository.IProductRepository;
import com.example.repository.IStoreRepository;

@Service
public class FeedServiceImpl implements IFeedService {
	
	@Autowired
	private IFeedRepository feedRepository;
	
	@Autowired
	private IMerchantRepository merchantRepository;
	
	@Autowired 
	private IProductRepository productRepository;
	
	@Autowired
	private IStoreRepository storeRepository;

	@Autowired
	FeedAssembler feedAssembler;

	@Override
	public List<Feed> viewAll() {
		List<Feed> feedList = feedRepository.findAll();
		return feedList;
	}

	@Override
	public Feed viewOne(Long id) {
		Feed feed = feedRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feed", "Feed ID", id));
		return feed;
	}

	@Override
	public Feed update(FeedDto feedDto,Long id) {
		Feed feed = feedRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Feed", "Feed ID", id));
		
		Merchant merchant = merchantRepository.getOne(feedDto.getMerchantId());
		feed.setMerchant(merchant);
		
		Product product = productRepository.getOne(feedDto.getProductId());
		feed.setProduct(product);
		
		Store store = storeRepository.getOne(feedDto.getStoreId());
		feed.setStore(store);
		
		feed.setCreatedAt(new Date());
		feed.setEndDate(feedDto.getEndDate());
		feed.setPrice(feedDto.getPrice());
		feed.setQuantity(feedDto.getQuantity());
		feed.setSalePrice(feedDto.getSalePrice());
		feed.setStartDate(feedDto.getStartDate());
		feedRepository.save(feed);
		return feed;
	}

	@Override
	public void delete(Long id) {
		Feed feed = feedRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Feed", "Feed ID", id));
		feedRepository.delete(feed);
	}

	@Override
	public Feed create(FeedDto feedDto) {
		Feed feed = feedAssembler.createFeedEntity(feedDto);
		feed.setCreatedAt(new Date());
		feedRepository.save(feed);
		return feed;
	}

	

}
