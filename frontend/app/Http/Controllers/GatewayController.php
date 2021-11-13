<?php

namespace App\Http\Controllers;

use App\Services\GatewayService;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Session;
use Exception;

class GatewayController extends Controller
{

    private $gatewayService;

    public function __construct(GatewayService $gatewayService)
    {
        $this->gatewayService = $gatewayService;
    }

    public function login(Request $request)
    {
        try {
            $response = json_decode($this->gatewayService->login($request->all()));
            $token = $response->data->access_token;
            $email = $response->data->user->email;
            Session::put( 'token', $token );
            Session::put( 'email', $email );
            //$value = $request->session()->get('token');
            return redirect()->route('home');

        } catch (Exception $e){
            return redirect()->route('login')->withErrors('User or password are incorrect');
        }
    }

    public function logout(Request $request)
    {
            $request->session()->flush();
            //$value = $request->session()->get('token');
            return redirect()->route('login');
    }

    public function register(Request $request)
    {
        try {
            $this->gatewayService->register($request->all());
            return redirect()->route('login')->with('status', 'User created!');

        } catch (Exception $e){
            return redirect()->route('register')->withErrors('Error');
        }
    }

}
