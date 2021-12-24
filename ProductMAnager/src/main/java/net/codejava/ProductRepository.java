package net.codejava;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  // các phươngn thức crud tưởng tác với enttity
}
