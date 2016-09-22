package org.zanata.rest.dto;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.zanata.common.LocaleId;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
public class UserTest {

    ObjectMapper om = new ObjectMapper();

    @Test
    public void testJsonOutput() throws IOException {
        String json =
            "{\"username\":\"_username\",\"email\":\"test@example.com\",\"name\":\"testUser\",\"imageUrl\":\"url\",\"languageTeams\":[{\"localeId\":\"zh-Hans\",\"displayName\":\"Chinese (Simplified)\",\"alias\":\"\",\"nativeName\":\"中文（简体）\",\"enabled\":true,\"enabledByDefault\":true,\"membersCount\":3},\n" +
                "{\"localeId\":\"fr\",\"displayName\":\"French\",\"alias\":\"\",\"nativeName\":\"français\",\"enabled\":true,\"enabledByDefault\":true,\"membersCount\":3}],\"loggedIn\":true}";
        User user = om.readValue(json, User.class);
        ArrayList<LocaleDetails> list = new ArrayList<LocaleDetails>();

        list.add(new LocaleDetails(new LocaleId("zh-Hans"), "Chinese (Simplified)", "", "中文（简体）", true, true, 3));
        list.add(new LocaleDetails(LocaleId.FR, "French", "", "français", true, true, 3));

        User expected = new User("_username", "test@example.com", "testUser",
                "url", list);

        assertThat(user, equalTo(expected));
    }
}
