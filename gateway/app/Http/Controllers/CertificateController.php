<?php

namespace App\Http\Controllers;

use App\Services\CertificateService;
use Illuminate\Http\Request;

class CertificateController extends Controller
{

    private $certificateService;

    public function __construct(CertificateService $certificateService)
    {
        $this->certificateService = $certificateService;
    }

    public function generateCertificate(Request $request)
    {
        return $this->successResponse($this->certificateService->generate($request->all()));
    }

    public function validateCertificate($certificate)
    {
        return $this->successResponse($this->certificateService->validate($certificate));
    }

}
