<?php

namespace App\Http\Controllers;

use App\Services\RegisterService;
use Illuminate\Http\Request;

class RegisterController extends Controller
{

    private $registerService;

    public function __construct(RegisterService $registerService)
    {
        $this->registerService = $registerService;
    }

    public function store(Request $request)
    {
        return $this->successResponse($this->registerService->register($request->all()));
    }

}
