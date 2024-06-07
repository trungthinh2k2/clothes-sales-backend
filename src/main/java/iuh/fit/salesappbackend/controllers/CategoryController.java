package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.CategoryDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.mappers.CategoryMapper;
import iuh.fit.salesappbackend.models.Category;
import iuh.fit.salesappbackend.service.interfaces.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @PostMapping
    public ResponseSuccess<?> createAddress(@RequestBody @Valid CategoryDto categoryDto) {
        Category category = categoryMapper.CategoryDto2Category(categoryDto);
        return new ResponseSuccess<>(HttpStatus.OK.value(),
                "Category created successfully",
                categoryService.save(category));
    }

    @GetMapping
    public ResponseSuccess<?> getAllCategories() {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get all categories successfully",
                categoryService.findAll()
        );
    }

    @PutMapping("/{id}")
    public ResponseSuccess<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.CategoryDto2Category(categoryDto);
        category.setId(id);
        return new ResponseSuccess<>(HttpStatus.OK.value(),
                "Category updated successfully",
                categoryService.update(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseSuccess<>(
                HttpStatus.NO_CONTENT.value(),
                "Category deleted successfully with id: " + id);
    }

    @PatchMapping("/{id}")
    public ResponseSuccess<?> patchCategory(@PathVariable Long id, Map<String, ?> data) {
        return new ResponseSuccess<>(HttpStatus.OK.value(),
                "Category patched successfully",
                categoryService.updatePatch(id, data));
    }
}
