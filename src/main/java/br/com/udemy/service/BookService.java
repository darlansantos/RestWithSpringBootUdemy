package br.com.udemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.converter.DozerConverter;
import br.com.udemy.domain.Book;
import br.com.udemy.exception.ResourceNotFoundException;
import br.com.udemy.repository.BookRepository;
import br.com.udemy.vo.BookVO;
import lombok.var;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public BookVO findById(Long id) {
		var entity = bookRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(bookRepository.findAll(), BookVO.class);
	}

	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class); // var = BookVO
		var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
		return vo;
	}
	
	public BookVO update(BookVO book) {
		return bookRepository
		.findById(book.getKey())
		.map(entity -> {
			entity.setAuthor(book.getAuthor());
			entity.setLaunchDate(book.getLaunchDate());
			entity.setPrice(book.getPrice());
			entity.setTitle(book.getTitle());
			return DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
		})
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

	public void delete(Long id) {
		bookRepository.findById(id)
		.map(book -> {
			bookRepository.delete(book);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
}
