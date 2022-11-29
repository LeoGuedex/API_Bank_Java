package leoguedex.com.github.JavaBank.repository;

import leoguedex.com.github.JavaBank.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

}
