package br.com.braslar.api.domain.odf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OdfRepository extends JpaRepository<Odf, Long> {

    Odf getReferenceByOdf(String odf);

    @Query(value = """
            select o.id from Odf o
            where
            o.odf = :odf
            """)
    String findIdByOdf(String odf);

    Odf getReferenceById(String idOdf);

}
