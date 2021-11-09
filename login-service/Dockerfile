FROM php:7.4
RUN apt-get update -y && apt-get install -y libpq-dev openssl zip unzip git curl
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

RUN docker-php-ext-install mysqli pdo pdo_mysql

WORKDIR /app
COPY . /app
RUN composer install

CMD php -S 0.0.0.0:8000 -t public
EXPOSE 8000
