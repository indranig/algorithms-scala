package org.awong

package object graphs {
	
	trait EdgeLike[V] {
		def v: V
		def w: V
	}
}