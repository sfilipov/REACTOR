# **List of changes to the UML** #

----------

## **Changes from initial UML to a4137d1618** ###

### *Changes to simulator.PlantComponent* ###
#### Changed fields ####
	+isOperational -> +operational
#### New fields ####
	+pressurised : Boolean
	+input       : PlantComponent
	+output      : PlantComponent
	+flowOut     : Flow
#### Changed methods ####
	+checkFailures() -> +checkFailure()
#### New methods ####
	+getFailureRate()					 : Float
	+setFailureRate(failureRate : Float) : void
	+getRepairTime()					 : Float
	+setRepairTime(repairTime : Integer) : void
	+getInput()							 : PlantComponent
	+setInput()							 : PlantComponent
	+getOutput()						 : PlantComponent
	+setOutput()						 : PlantComponent
	+getFlowOut()						 : Flow
	+isOperational()					 : Boolean
	+isPressurised()					 : Boolean

### *Changes to simulator.Valve* ###
#### Changed fields ####
	Remove +connectedTo (use input and output from PlantComponent instead)
	+isOpen -> +open
#### Changed methods ####
	+open()			-> setOpen(open : Boolean) : void
	+close()		-> setOpen(open : Boolean) : void
	+toggleState()	-> setOpen(open : Boolean) : void
#### New methods ####
	+isOpen() : Boolean

### *Changes to simulator.Pump* ###
#### Changed fields ####
	Remove +source and +destination
#### New fields ####
	+on : Boolean
#### Changed methods ####
	+turnOn()		-> setOn(on : Boolean) : void
	+turnOff()		-> setOn(on : Boolean) : void
	+toggleState()	-> setOn(on : Boolean) : void
#### New methods ####
	+getRpm()				: Integer
	+setRpm(rpm : Ingeter) 	: void
	+getOn()				: Boolean

### *Changes to simulator.Repair* ###
#### New methods ####
	+getTimeStepsRemaining() : Integer
	+decTimeStepsRemaining() : void
	+getPlantComponent() : PlantComponent

### *New classes* ###
* ConnectorPipe
* Flow
* GameInit
* Interface
* PlantPresenter

### **Other classes will also have changes but they are TBD and implemented** ###