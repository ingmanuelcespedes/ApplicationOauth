package COM.ENT;

import COM.ENT.McUsers;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-01T13:21:14")
@StaticMetamodel(McTypeOauth.class)
public class McTypeOauth_ { 

    public static volatile SingularAttribute<McTypeOauth, Character> mcTypeOauthStatus;
    public static volatile SingularAttribute<McTypeOauth, String> mcTypeOauthAlterNetwork;
    public static volatile SingularAttribute<McTypeOauth, String> mcTypeOauthAlterUser;
    public static volatile SingularAttribute<McTypeOauth, String> mcTypeOauthDescription;
    public static volatile SingularAttribute<McTypeOauth, String> mcTypeOauthAlterDate;
    public static volatile SingularAttribute<McTypeOauth, Integer> mcTypeOauthId;
    public static volatile ListAttribute<McTypeOauth, McUsers> mcUsersList;

}