package vn.iotstar.example1.mapper;

import org.mapstruct.Mapper;
import vn.iotstar.example1.dto.UserHeaderView;
import vn.iotstar.example1.entity.AppUser;

@Mapper(componentModel = "spring")
public interface UserMapper { UserHeaderView toHeaderView(AppUser user); }
