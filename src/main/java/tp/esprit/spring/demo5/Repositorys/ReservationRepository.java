package tp.esprit.spring.demo5.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tp.esprit.spring.demo5.entity.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long>
{
    @Query("select r from Reservation r join Chambre c on r member of c.reservations where (c.bloc.idBloc = :idBloc and " +
            "year(r.anneeUniversitaire) = year(current_date) and r.estValide = true) order by r.idReservation limit 1")
    Reservation findForReservation(long idBloc);

    @Query("select r from Reservation r join Etudiant e on e member of r.etudiants where e.cin = :cinEtudiant and" +
            " year(r.anneeUniversitaire) = :anneeUniversitaire")
    Reservation findByEtudiantsCinEtudiantAndAnneeUniversitaire(long cinEtudiant, int anneeUniversitaire);
    @Query("select r from Reservation r join Chambre c on r member of c.reservations where extract(year from r.anneeUniversitaire) " +
            "= :anneeUniversitaire and c.bloc.foyer.universite.nomUniversite = :nomUniversite ")
    List<Reservation> findByAnneeUniversitaire_YearAndNomUnuiversite(int anneeUniversitaire, String nomUniversite);}
