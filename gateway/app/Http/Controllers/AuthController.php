<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AuthController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }

    public function login(Request $request) {
        $body= $request->only('email','password');

        if (! $token = Auth::attempt($body)) {
            return $this->sendJsonErrorResponse("Invalid username or password", 401);
        }

        $responseData = [
            'user' => Auth::user(),
            'access_token' => $token,
            'token_type' => 'bearer',
            'expires_in' => Auth::factory()->getTTL() * 60
        ];

        return $this->sendFormattedJsonResponse($responseData);
    }


}
