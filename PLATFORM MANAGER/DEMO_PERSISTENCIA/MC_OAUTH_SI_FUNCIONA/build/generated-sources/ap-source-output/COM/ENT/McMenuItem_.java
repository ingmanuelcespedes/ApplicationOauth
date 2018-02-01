package COM.ENT;

import COM.ENT.McUsersItemMenu;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-01T13:21:14")
@StaticMetamodel(McMenuItem.class)
public class McMenuItem_ { 

    public static volatile SingularAttribute<McMenuItem, String> mcMenuItemAlterDate;
    public static volatile SingularAttribute<McMenuItem, Character> mcMenuItemStatus;
    public static volatile ListAttribute<McMenuItem, McUsersItemMenu> mcUsersItemMenuList;
    public static volatile SingularAttribute<McMenuItem, String> mcMenuItemName;
    public static volatile SingularAttribute<McMenuItem, Integer> mcMenuItemId;
    public static volatile SingularAttribute<McMenuItem, String> mcMenuItemAlterNetwork;
    public static volatile SingularAttribute<McMenuItem, String> mcMenuItemAlterUser;

}