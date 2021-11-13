<?php

namespace App\Http\Controllers;

use App\Services\GatewayService;

class EmailController extends Controller
{

    private $gatewayService;

    public function __construct(GatewayService $gatewayService)
    {
        $this->gatewayService = $gatewayService;
        $this->middleware('auth');
    }

    public function send($to, $subject, $message)
    {
        $data = [
            'to' => $to,
            'subject' => $subject,
            'message' => $message
        ];

        $this->gatewayService->sendEmail($data);
    }

}
