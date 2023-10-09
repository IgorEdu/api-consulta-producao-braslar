package br.com.braslar.api.domain.etiqueta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {

    Etiqueta getReferenceBySerie(String serie);

    @Query(value = """
            select e.id from Etiqueta e
            where
            e.serie = :serie
            """)
    String findIdBySerie(String serie);

    Etiqueta getReferenceById(String idEtiqueta);

}
