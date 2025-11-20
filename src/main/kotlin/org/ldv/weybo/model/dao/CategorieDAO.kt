package org.ldv.weybo.model.dao

import org.ldv.weybo.model.entity.Categorie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategorieDAO : JpaRepository<Categorie, Long>




