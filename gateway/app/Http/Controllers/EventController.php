<?php

namespace App\Http\Controllers;

use App\Services\EventService;
use Illuminate\Http\Request;

class EventController extends Controller
{

    private $eventService;

    public function __construct(EventService $eventService)
    {
        $this->eventService = $eventService;
        return $this->middleware('auth');
    }

    public function registration(Request $request)
    {
        return $this->successResponse($this->eventService->registration($request->all()));
    }

    public function cancelRegistration(Request $request)
    {
        return $this->successResponse($this->eventService->cancelRegistration($request->all()));
    }

    public function index()
    {
        return $this->successResponse($this->eventService->getEvents());
    }

    public function getInscriptions()
    {
        return $this->successResponse($this->eventService->getInscriptions());
    }

    public function getSingleInscription($id)
    {
        return $this->successResponse($this->eventService->getSingleInscription($id));
    }

}
