INSTRUCTION:
1. Go to the src\main\java\com\mkaloshyn\myPostsApp\MyPostsApp.java
2. Run myPostsApp.main()
3. Follow the instructions on your console.

WRAP-UP
used the next programming patterns: template, singleton, builder.
used Stream API

TASK:
Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

User (id, firstName, lastName, List<Post> posts, role)
Post (id, content, created, updated, region)
Region (id, name)
Role (enum ADMIN, MODERATOR, USER)

В качестве хранилища данных необходимо использовать CSV файлы:
users.csv, posts.csv, regions.csv

Также добавлен файл postsToUsers.csv описывающий приналежность каждого поста конкретному юзеру
(у одного поста - только один юзер, у одного юзера может быть несколько постов или не быть вообще).

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

Слои:
model - POJO класы
repository - классы, реализующие доступ к текстовым файлам
controller - обработка запросов от пользователя
view - все данные, необходимые для работы с консолью

Например: User, UserRepository, UserController, UserView и т.д.

Для репозиторного слоя желательно использовать базовый интерфейс:
interface GenericRepository<T,ID>

Interface UserRepository extends GenericRepository<User, Long>

class CsvUserRepositoryImpl implements UserRepository

В рамках данного проекта необходимо активно использовать возможности Stream API и шаблоны проектирования.

Результатом выполнения задания должен быть отдельный слой приложения, реализованного в рамках модуля
Java IO/NIO под названием csv.
Слой controller должен использовать csv реализацию.
