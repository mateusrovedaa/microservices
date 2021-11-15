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
    $router->get('/inscriptions', ['uses' => 'EventController@getInscriptions']);
    $router->post('/inscriptions', ['uses' => 'EventController@getSingleInscription']);
    $router->get('/', ['uses' => 'EventController@index']);
    $router->get('/{id}', ['uses' => 'EventController@getSingleEvent']);
    $router->post('/registration', ['uses' => 'EventController@registration']);
    $router->post('/cancelregistration', ['uses' => 'EventController@cancelregistration']);
});
$router->group(['prefix' => 'certificate'], function () use ($router) {
    $router->group(['middleware' => ['auth']], function () use ($router) {
        $router->post('/certificate', ['uses' => 'CertificateController@generateCertificate']);
    });
    $router->get('/validate/{certificate}', ['uses' => 'CertificateController@validateCertificate']);
});
$router->group(['prefix' => 'users'], function () use ($router) {
    $router->group(['middleware' => ['auth']], function () use ($router) {
        $router->get('/', ['uses' => 'UserController@index']);
    });
    $router->post('/single', ['uses' => 'UserController@getSingle']);
});
