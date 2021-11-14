<?php

namespace App\Http\Controllers;

use App\Services\UserService;

class UserController extends Controller
{

    private $userService;

    public function __construct(UserService $userService)
    {
        $this->userService = $userService;
        return $this->middleware('auth');
    }


    public function index()
    {
        return $this->successResponse($this->userService->getUsers());
    }

}
