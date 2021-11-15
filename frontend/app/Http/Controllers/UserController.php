<?php

namespace App\Http\Controllers;

use App\Services\GatewayService;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Session;
use Exception;

class UserController extends Controller
{

    private $gatewayService;

    public function __construct(GatewayService $gatewayService)
    {
        $this->gatewayService = $gatewayService;
        $this->middleware('auth');
    }

    public function getData()
    {
        $user = $this->gatewayService->getUser(Session::get('email'));
        $jsonuser = json_decode($user, true);
        return view('user.update', compact('jsonuser'));
    }

    public function update(Request $request)
    {
        try {
            $this->gatewayService->updateUser($request->all());
            return redirect()->route('home')->with('status', 'User data updated!');
        } catch (Exception $e){
            return redirect()->route('home')->withErrors('Error and user data update.');
        }
    }

}
