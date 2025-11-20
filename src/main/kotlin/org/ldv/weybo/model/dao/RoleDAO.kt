package org.ldv.weybo.model.dao


import org.ldv.weybo.model.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleDAO : JpaRepository<Role, Long>
