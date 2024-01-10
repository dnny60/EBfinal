package com.example.EBDEMO.Repository;


import org.springframework.data.repository.CrudRepository;

import com.example.EBDEMO.model.PlantingDetail;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PlantingDetailRepository extends CrudRepository<PlantingDetail, Long> {
	
//	// 檢查是否存在特定 TeaID 的 Inventory 記錄 
//	boolean existsByTeaId(Long teaId);
//	
//	// 根據 TeaID 刪除 Inventory 記錄 
//	void deleteByTeaId(Long teaId);

}