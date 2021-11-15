<?php

namespace App\Http\Controllers;

use App\Services\GatewayService;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Session;
use Exception;

class CertificateController extends Controller
{

    private $gatewayService;

    public function __construct(GatewayService $gatewayService)
    {
        $this->gatewayService = $gatewayService;
    }

    public function validateCertificate($id)
    {
        try {
            $certificate = $this->gatewayService->validateCertificate($id);
            $jsoncertificate = json_decode($certificate, true);
            $event = $this->gatewayService->getSingleEvent($jsoncertificate['data']['event_id']);
            $jsonevent = json_decode($event, true);
            $user = $this->gatewayService->getUser($jsoncertificate['data']['user_email']);
            $jsonuser = json_decode($user, true);
            return view('events.certificate', compact('jsoncertificate', 'jsonevent', 'jsonuser'));

        } catch (Exception $e){
            return view('events.certificate');
        }
    }

}
