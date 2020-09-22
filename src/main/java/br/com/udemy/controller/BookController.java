package br.com.udemy.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemy.service.BookService;
import br.com.udemy.vo.BookVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", description = "Description for book", tags = { "BookEndpoint" })
@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@ApiOperation(value = "Find all books recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	@ResponseStatus(HttpStatus.OK)
	public List<BookVO> findAll() {
		List<BookVO> books = bookService.findAll();
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	@ApiOperation(value = "Find a specific book by your ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	@ResponseStatus(HttpStatus.OK)
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = bookService.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}

	@ApiOperation(value = "Create a new book")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	@ResponseStatus(HttpStatus.CREATED)
	public BookVO create(@RequestBody BookVO book) {
		BookVO bookVO = bookService.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return bookVO;
	}

	@ApiOperation(value = "Update a specific book")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public BookVO update(@RequestBody BookVO book) {
		BookVO bookVO = bookService.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return bookVO;
	}

	@ApiOperation(value = "Delete a specific book by your ID")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		bookService.delete(id);
	}

}
