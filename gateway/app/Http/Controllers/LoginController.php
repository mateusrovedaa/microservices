<?php

namespace App\Http\Controllers;

use App\Services\LoginService;
use Illuminate\Http\Request;

class LoginController extends Controller
{

    private $loginService;

    public function __construct(LoginService $loginService)
    {
        $this->loginService = $loginService;
    }

    public function store(Request $request)
    {
        return $this->successResponse($this->loginService->login($request->all()));
    }

}
