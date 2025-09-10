package tuan02.vd2.service.impl;

import java.util.List;

import tuan02.vd2.dao.CategoryDAO;
import tuan02.vd2.dao.impl.CategoryDAOImpl;
import tuan02.vd2.model.Category;
import tuan02.vd2.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    CategoryDAO dao = new CategoryDAOImpl();

    @Override
    public boolean insert(Category category) {
        try {
            dao.insert(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        try {
            dao.edit(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            dao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Category findById(int id) {
        return dao.get(id);
    }

    @Override
    public Category findByName(String name) {
        return dao.get(name);
    }

    @Override
    public List<Category> findAll() {
        return dao.getAll();
    }

    @Override
    public List<Category> search(String keyword) {
        return dao.search(keyword);
    }

    @Override
    public boolean isNameExists(String name, int excludeId) {
        List<Category> categories = dao.getAll();
        for (Category c : categories) {
            if (c.getCatename().equalsIgnoreCase(name) && c.getCateid() != excludeId) {
                return true;
            }
        }
        return false;
    }
}