package tuan02.vd2.service;

import java.util.List;
import tuan02.vd2.model.Category;

public interface CategoryService {
    boolean insert(Category category);

    boolean update(Category category);   // trả về true/false để kiểm tra update thành công

    boolean delete(int id);

    Category findById(int id);

    Category findByName(String name);

    List<Category> findAll();

    List<Category> search(String keyword);

    boolean isNameExists(String name, int excludeId); // check trùng tên (trừ id hiện tại)
}