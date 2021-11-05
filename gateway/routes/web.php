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

// $router->group(['prefix' => 'api'], function () use ($router) {

//     $router->post('login', 'AuthController@login');

// });
$router->group(['prefix' => 'register'], function () use ($router) {
    $router->post('/', ['uses' => 'RegisterController@store']);
});
$router->group(['prefix' => 'login'], function () use ($router) {
    $router->post('/', ['uses' => 'LoginController@store']);
});
$router->group(['prefix' => 'checkin'], function () use ($router) {
    $router->post('/', ['uses' => 'CheckinController@store']);
});
$router->group(['prefix' => 'email'], function () use ($router) {
    $router->post('/', ['uses' => 'EmailController@store']);
});
$router->group(['prefix' => 'event'], function () use ($router) {
    $router->get('/', ['uses' => 'EventController@index']);
    $router->get('/{id}', ['uses' => 'EventController@getSingle']);
    $router->post('/registration', ['uses' => 'EventController@registration']);
    $router->post('/cancelregistration', ['uses' => 'EventController@cancelregistration']);
});
