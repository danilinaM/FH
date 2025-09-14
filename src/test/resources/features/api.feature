# language: ru

Функционал: API личного кабинета Fitness House

  Сценарий: Получение информации о пользователе
    * получаем токен testUser
    Когда отправляем GET запрос на "api/user"
    То получаем данные пользователя:
      | поле              | значение         |
      | статус            | 200              |
      | id                | 784066           |
      | gcid              | 578228           |
      | name              | Иван Петров      |
      | email             | qwer@gmail.com   |
      | phone             | 9964514907       |
      | phone_verified_at | 2025-05-25 16:00 |
      | created_at        | 2025-05-25 13:00 |
      | initials          | ИП               |

  Сценарий: Получение профиля пользователя
    * получаем токен testUser
    Когда отправляем GET запрос на "api/user/profile"
    То получаем данные пользователя:
      | поле       | значение       |
      | статус     | 200            |
      | lastname   | Петров         |
      | firstname  | Иван           |
      | middlename | Иванович       |
      | phone      | 9964514907     |
      | email      | qwer@gmail.com |
      | birthday   | 2000-01-10     |
      | sex        | M              |
      | rejectmc   | false          |
      | model      | клиент         |
      | activity   | false          |
