package COM.ENT;

import COM.ENT.McSessionOauth;
import COM.ENT.McTypeOauth;
import COM.ENT.McUsersItemMenu;
import COM.ENT.McUsersMenuOauth;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-30T19:03:17")
@StaticMetamodel(McUsers.class)
public class McUsers_ { 

    public static volatile ListAttribute<McUsers, McSessionOauth> mcSessionOauthList;
    public static volatile SingularAttribute<McUsers, String> mcUsersAlterDate;
    public static volatile SingularAttribute<McUsers, String> mcUsersName;
    public static volatile SingularAttribute<McUsers, String> mcUsersLogin;
    public static volatile SingularAttribute<McUsers, Integer> mcUsersId;
    public static volatile SingularAttribute<McUsers, String> mcUsersBirthDate;
    public static volatile SingularAttribute<McUsers, McTypeOauth> mcUsersType;
    public static volatile ListAttribute<McUsers, McUsersItemMenu> mcUsersItemMenuList;
    public static volatile SingularAttribute<McUsers, Character> mcUsersGender;
    public static volatile SingularAttribute<McUsers, String> mcUsersAlterUser;
    public static volatile ListAttribute<McUsers, McUsersMenuOauth> mcUsersMenuOauthList;
    public static volatile SingularAttribute<McUsers, String> mcUsersDescription;
    public static volatile SingularAttribute<McUsers, String> mcUsersAlterNetwork;
    public static volatile SingularAttribute<McUsers, String> mcUsersPass;

}