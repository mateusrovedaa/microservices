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

$router->post('registration', 'EventRegistrationController@registration');
$router->post('cancelregistration', 'EventRegistrationController@cancelRegistration');
$router->get('inscriptions', 'EventRegistrationController@getAll');
$router->post('inscriptions', 'EventRegistrationController@getSingle');
$router->get('events', 'EventRegistrationController@getAllEvents');
$router->get('event/{id}', 'EventRegistrationController@getSingleEvent');


