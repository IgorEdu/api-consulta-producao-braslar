package br.com.braslar.api.domain.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto getReferenceById(String string);

    Produto getReferenceByCodigo(String codigo);

    @Query(value = """
            select p.id from Produto p
            where
            p.codigo = :codigo
            and p.empresa = :empresa
            and p.local = :local
            """)
    String findIdByCodigoAndEmpresaAndLocal(String codigo, String empresa, String local);
}
