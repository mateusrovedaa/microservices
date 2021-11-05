<?php

namespace App\Http\Controllers;

use App\Services\EmailService;
use Illuminate\Http\Request;

class EmailController extends Controller
{

    private $emailService;

    public function __construct(EmailService $emailService)
    {
        $this->emailService = $emailService;
        return $this->middleware('auth');
    }

    public function store(Request $request)
    {
        return $this->successResponse($this->emailService->sendEmail($request->all()));
    }

}
