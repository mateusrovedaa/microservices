<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/login', function () {
    return view('auth/login');
});
Route::post('/login', [App\Http\Controllers\GatewayController::class, 'login'])->name('login');

Route::get('/register', function () {
    return view('auth/register');
});
Route::post('/register', [App\Http\Controllers\GatewayController::class, 'register'])->name('register');
Route::post('/logout', [App\Http\Controllers\GatewayController::class, 'logout'])->name('logout');

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');

Route::get('/list-events', [App\Http\Controllers\EventController::class, 'events'])->name('list-events');
Route::get('/inscription/{id}', [App\Http\Controllers\EventController::class, 'getSingleEvent'])->name('inscription');

Route::post('/inscription', [App\Http\Controllers\EventController::class, 'inscription'])->name('inscription');
Route::get('/list-inscriptions', [App\Http\Controllers\EventController::class, 'listInscriptions'])->name('list-inscriptions');
Route::post('/cancelinscription', [App\Http\Controllers\EventController::class, 'cancelInscription'])->name('cancel-inscription');
Route::post('/certificate', [App\Http\Controllers\EventController::class, 'certificate'])->name('certificate');
Route::get('/validate/{id}', [App\Http\Controllers\CertificateController::class, 'validateCertificate'])->name('validate');
