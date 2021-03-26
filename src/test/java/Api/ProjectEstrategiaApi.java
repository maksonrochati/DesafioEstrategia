package Api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class ProjectEstrategiaApi {

    private String token;
    private String userId;

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://54.174.86.218";

        // Criar novo usuário
        userId = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"name\":\"Makson Rocha\",\n" +
                        "  \"email\": \"maksonocha@gmail.com\",\n" +
                        "  \"password\": \"123456\"\n" +
                        "}")
                .when()
                    .post("auth/register")
                .then()
                    .extract()
                        .path("user._id");

        // Autentica usuário
         token = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                        .body("{\n" +
                             "  \"email\": \"maksonrocha@gmail.com\",\n" +
                             "  \"password\": \"123456\"\n" +
                            "}")
                .when()
                    .post("auth/authenticate")
                .then()
                    .extract()
                        .path("token");

    }

    @Test
    public void testCriarNovoProjeto(){
        RestAssured
                .given()
                    .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                        "  \"title\": \"Test\",\n" +
                        "  \"description\": \"Teste\",\n" +
                        "  \"tasks\": [\n" +
                        "    {\n" +
                        "      \"name\": \"mkr\",\n" +
                        "      \"assignedTo\":  \n" + userId +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                    .post("projects")
                .then()
                    .assertThat()
                        .statusCode(200);


    }

    @Test
    public void testBuscarTodosOsProjetos(){

        RestAssured
                .given()
                    .header("Authorization", "Bearer " + token)
                .when()
                    .get("projects")
                .then()
                    .assertThat()
                        .statusCode(200);

    }

    @Test
    public void testBuscarProjetoEspecifico(){

        RestAssured
                .given()
                    .header("Authorization", "Bearer " + token)
                .when()
                    .get("projects/5fd8a5e8127b1315ddb39752")
                .then()
                    .assertThat()
                        .statusCode(200)
                            .body("project._id", Matchers.equalTo("5fd8a5e8127b1315ddb39752"));

    }

    @Test
    public void testAlterarProjetoEspecifico(){
        RestAssured
                .given()
                    .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                        "  \"title\": \"Novo titulo\",\n" +
                        "  \"description\": \"Alteração\",\n" +
                        "  \"tasks\": [\n" +
                        "    {\n" +
                        "      \"name\": \"Proj Test\",\n" +
                        "      \"assignedTo\": \"5fd8a5e8127b1315ddb39752\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                    .put("projects/5fd8a5e8127b1315ddb39752")
                .then()
                    .assertThat()
                         .statusCode(200)
                            .body("project.title", Matchers.equalTo("Novo titulo"));


    }


    @Test
    public void testDeletarProjeto(){

        RestAssured
                .given()
                    .header("Authorization", "Bearer " + token)
                .when()
                    .get("projects/5fd8a5e8127b1315ddb39752")
                .then()
                    .assertThat()
                        .statusCode(200);

    }


}
