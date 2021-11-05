<?php

namespace App\Http\Controllers;

use App\Services\CheckinService;
use Illuminate\Http\Request;

class CheckinController extends Controller
{

    private $checkinService;

    public function __construct(CheckinService $checkinService)
    {
        $this->checkinService = $checkinService;
        return $this->middleware('auth');
    }

    public function store(Request $request)
    {
        return $this->successResponse($this->checkinService->checkin($request->all()));
    }

}
