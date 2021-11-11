# Lumen Auth API

Lumen is a basic API for user authentication and registration built with Lumen

## Installation

Please check the official Lumen installation guide for server requirements before you start. [Official Documentation](https://lumen.laravel.com/docs/8.x/installation)


Clone the repository

    git clone https://github.com/prismathic/lumen-auth-api.git

Switch to the repo folder

    cd lumen-auth-api

Install all the dependencies using composer

    composer install

Copy the example env file and make the required configuration changes in the .env file

    cp .env.example .env

Generate a new application key

    php artisan key:generate

Generate a new random JWT secret

    php artisan jwt:secret

Run the database migrations (**Set the database connection in .env before migrating**)

    php artisan migrate

Start the local development server

    php -S localhost:8000 -t public

Run `php artisan db:seed` to seed a default users into the database

You can now access the API at http://localhost:8000/api/v1

## Docs

To access this API's documentation, open the `index.html` file in the `public/docs` directory.

## Testing

Run `vendor/bin/phpunit` in the project directory to run tests on the application

## Cheers

Have fun building :)
