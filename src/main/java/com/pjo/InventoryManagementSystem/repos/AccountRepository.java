package com.pjo.InventoryManagementSystem.repos;

import com.pjo.InventoryManagementSystem.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
