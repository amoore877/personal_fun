package ruu.hw5;

public enum TransitionType {

	/** No transition; first node. **/
	None,
	/** Transitioned from tail to head to get to this node. **/
	TAIL_TO_HEAD,
	/** Transitioned from head to tail to get to this node. **/
	HEAD_TO_TAIL;
}
