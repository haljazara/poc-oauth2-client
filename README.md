# Steps to run the client application:
1- Clone and run Catalog Admin Plus project
2- Register your client on catlog admin project by creating a new changeSet with client details under [resource/db/seed/db.seed.oauth-client.xml] as follow:
    <changeSet author="AUTHOR-NAME" id="LAST-ID">
        <insert tableName="oauth_client_details">
            <column name="client_id" value="YOUR-CLIENT-ID" />
            <column name="resource_ids" value="YOUR-CLIENT-RESOURCE-ID" />
            <column name="YOUR-CLIENT-SECRET"
                    value="Look into the generate client secret section"/>
            <column name="scope" value="read,write,trust" />
            <column name="authorized_grant_types"
                    value="password,authorization_code,refresh_token" />
            <column name="access_token_validity" valueNumeric="36000" />
            <column name="refresh_token_validity" valueNumeric="36000" />
            <column name="autoapprove" value="true" />
        </insert>
    </changeSet>
2- Update application.properties file by the required attributes
3- Set your resource id on ResourceServer class
3- Run the application: mvn clean package spring-boot:run

# Generate client secret:
- Run the following command on the database:
      SELECT CONCAT(SHA2('{ SALT }{ PASSWORD }', 256), ':{ SALT }:1') as client_secret;

# Steps to test the client application:
1- Generate user token JWT:
            curl --location --request POST '{ OAuth-Server-URL }/oauth/token' \
            --header 'Authorization:  Basic { Encrypt client id and client seceret with Base64}' \
            --header 'Content-Type: application/x-www-form-urlencoded' \
            --data-raw 'grant_type=password&username=CURRENT_ADMIN_EMAIL&password=CURRENT_ADMIN_PASSWORD'
            
2-  User info api will check your token and return welcome message:
            curl --location --request GET 'http://localhost:8082/api/user/info' \
            --header 'Authorization: Bearer { jwt-token }'
            
3- To check if the your apis will be protected by roles and rules then you will need to annotate it by @PreAuthorize("hasRole('role-name')")
