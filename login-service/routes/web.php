<?php

/** @var \Laravel\Lumen\Routing\Router $router */

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->group(['prefix' => 'auth'], function () use ($router) {
    $router->get('/', function () use ($router) {
        return $router->app->version();
    });
    $router->post('register', 'AuthController@register');
    $router->post('login', 'AuthController@login');

    $router->get('users', 'UserController@getAll');
    $router->get('users/{id}', 'UserController@getSingle');
    $router->get('profile', 'UserController@getUserProfile');
});
