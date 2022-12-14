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

    public function getSingleEvent($id)
    {
        return $this->successResponse($this->eventService->getSingleEvent($id));
    }

    public function getInscriptions()
    {
        return $this->successResponse($this->eventService->getInscriptions());
    }

    public function getSingleInscription(Request $request)
    {
        return $this->successResponse($this->eventService->getSingleInscription($request->all()));
    }

}
