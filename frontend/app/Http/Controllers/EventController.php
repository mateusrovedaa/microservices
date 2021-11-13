<?php

namespace App\Http\Controllers;

use App\Services\GatewayService;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Session;
use Exception;

class EventController extends Controller
{

    private $gatewayService;

    public function __construct(GatewayService $gatewayService)
    {
        $this->gatewayService = $gatewayService;
        $this->middleware('auth');
    }

    public function events(Request $request)
    {
            $events = $this->gatewayService->getEvents($request->all());
            $jsonevents = json_decode($events, true);
            return view('events.list-events', compact('jsonevents'));
    }

    public function getSingleEvent($id)
    {
        try {
            $event = $this->gatewayService->getSingleEvent($id);
            $jsonevents = json_decode($event, true);
            return view('events.inscription', compact('jsonevents'));

        } catch (Exception $e){
            return redirect()->route('list-events')->withErrors('Event cannot find');
        }
    }

    public function inscription(Request $request)
    {
        try {
            $this->gatewayService->inscription($request->all());
            return redirect()->route('list-events')->with('status', 'Inscription success!');

        } catch (Exception $e){
            return redirect()->route('list-events')->withErrors('User is already registered for this event');
        }
    }

    public function listInscriptions(Request $request)
    {
        $inscriptions = $this->gatewayService->getInscriptions($request->all());
        $events = $this->gatewayService->getEvents($request->all());
        $jsoninscriptions = json_decode($inscriptions, true);
        $jsonevents = json_decode($events, true);

        return view('inscriptions.list-inscriptions', compact( 'jsoninscriptions', 'jsonevents'));
    }

    public function cancelInscription(Request $request)
    {
        try {
            $this->gatewayService->cancelInscription($request->all());
            return redirect()->route('list-inscriptions')->with('status', 'Inscription canceled!');

        } catch (Exception $e){
            return redirect()->route('list-inscriptions')->withErrors('Sorry, it is only possible to cancel a registration up to 2 days before the event!');
        }
    }

}
