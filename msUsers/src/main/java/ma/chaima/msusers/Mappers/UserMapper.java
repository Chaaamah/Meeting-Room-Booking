package ma.chaima.msusers.Mappers;

import ma.chaima.msusers.DTOs.UserReqDTO;
import ma.chaima.msusers.DTOs.UserRespDTO;
import ma.chaima.msusers.Entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    // Conversion Entity vers DTO de réponse
    UserRespDTO fromUtilisateur(Utilisateur utilisateur);

    // Conversion DTO de requête vers Entity
    Utilisateur fromUserReqDTO(UserReqDTO userReqDTO);
}
